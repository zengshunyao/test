package javaBase.util;

/**
 * 使用Linux管理权限的类似方法去定义权限。
 * 每个权限单独的值都是2的n次幂。
 * 增加权限使用|操作。
 * 查看权限使用&操作。
 * 删除权限使用取反再与操作。
 * 使用enum特性使用得代码更加的简洁，而且控制了权限参数，不会出现误传入的情况。
 */
public class PermissionController {

    private int permissionValue = 0;

    // 初始化权限值
    public PermissionController(int permissionValue) {
        this.permissionValue = permissionValue;
    }

    // 添加权限
    public void addPermission(Permission p) {
        for (Permission t : Permission.values()) {
            if (t == p) {
                permissionValue |= p.getValue();
            }
        }
    }

    // 删除权限
    public void removePermission(Permission p) {
        for (Permission t : Permission.values()) {
            if (t == p) {
                permissionValue &= (~p.getValue());
            }
        }
    }

    // 判断是否有权限
    public boolean hasPermission(Permission p) {
        return (permissionValue & p.getValue()) == p.getValue();
    }

    // 返回权限的字符串表示
    public String permissionString() {
        StringBuilder sb = new StringBuilder(32);
        for (Permission p : Permission.values()) {
            if (hasPermission(p)) {
                sb.append(p.toString()).append(", ");
            }
        }
        return sb.lastIndexOf(",") == sb.length() - 2 ?
                sb.toString().substring(0, sb.lastIndexOf(",")) : sb.toString();
    }

    // 返回权限的数字值
    public int getPermissionValue() {
        return permissionValue;
    }

    // 使用enum来限定权限，限制参数的类型与值
    public static enum Permission {
        INSERT(1), UPDATE(2), DELETE(4); // 每个权限的值是2的n次幂
        private final int value;

        private Permission(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        // 传给构造函数的权限值从数据库中取得(这里为0，目前什么权限也没有)
        PermissionController p = new PermissionController(0);

        // 增加添加权限
        System.out.println("增加: 添加权限");
        p.addPermission(Permission.INSERT);
        System.out.printf("Can insert: %s\n", p.hasPermission(Permission.INSERT));

        // 增加删除权限
        System.out.println("增加: 删除权限");
        p.addPermission(Permission.DELETE);
        System.out.printf("Can delete: %s\n", p.hasPermission(Permission.DELETE));

        System.out.println(p.permissionString());

        System.out.printf("Can update: %s\n", p.hasPermission(Permission.UPDATE));
        System.out.println(Integer.toBinaryString(p.getPermissionValue()));

        System.out.println("去掉: 删除权限");
        // 去掉删除权限
        p.removePermission(Permission.DELETE);
        System.out.printf("Can delete: %s\n", p.hasPermission(Permission.DELETE));
        System.out.printf("Can insert: %s\n", p.hasPermission(Permission.INSERT));

        System.out.println(Integer.toBinaryString(p.getPermissionValue()));
        System.out.println(p.permissionString());
    }
}
