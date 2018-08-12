package com.funi.distributedcomputer.netty.rpc.provider;

import com.funi.distributedcomputer.netty.rpc.api.IRpcHello;

public class RpcHello implements IRpcHello {

	@Override
	public String hello(String name) {
		return "Hello , " + name + "!";
	}

}
