package com.funi.distributedcomputer.netty.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class RpcRegistry {

    //端口
    private int port;

    public RpcRegistry(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();

                            //处理的拆包、粘包的解、编码器
                            //解析带有长度属性的包
                            //TCP以流的方式进行数据传输，上层应用协议为了对消息进行区分，往往采用如下4种方式。
                            // （1）消息长度固定：累计读取到固定长度为LENGTH之后就认为读取到了一个完整的消息。
                            //     然后将计数器复位，重新开始读下一个数据报文。
                            // （2）回车换行符作为消息结束符：在文本协议中应用比较广泛。
                            // （3）将特殊的分隔符作为消息的结束标志，回车换行符就是一种特殊的结束分隔符。
                            // （4）通过在消息头中定义长度字段来标示消息的总长度。
                            // netty中针对这四种场景均有对应的解码器作为解决方案，比如：
                            // （1）通过FixedLengthFrameDecoder 定长解码器来解决定长消息的黏包问题；
                            // （2）通过LineBasedFrameDecoder和StringDecoder来解决以回车换行符作为消息结束符的TCP黏包的问题；
                            // （3）通过DelimiterBasedFrameDecoder 特殊分隔符解码器来解决以特殊符号作为消息结束符的TCP黏包问题；
                            // （4）最后一种，也是本文的重点，通过LengthFieldBasedFrameDecoder 自定义长度解码器解决TCP黏包问题。
                            pipeline.addLast(new LengthFieldBasedFrameDecoder
                                    (Integer.MAX_VALUE, 0, 4,
                                            0, 4));


                            pipeline.addLast(new LengthFieldPrepender(4));

                            //处理序列化的解、编码器（JDK默认的序列化）
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));


                            //自己的业务逻辑
                            pipeline.addLast(new RegistryHandler());

                        }

                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);


            ChannelFuture f = b.bind(this.port).sync();

            System.out.println("RPC Registry start listen at " + this.port);

            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new RpcRegistry(8080).start();
    }
}
