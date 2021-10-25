package com.laus.permissions;

import com.laus.permissions.exception.ChainEndException;
import com.laus.permissions.model.Permission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PermissionTests {

    @Test()
    public void validatePermission() {
        Permission permission1 = new Permission("Create");
        Permission permission2 = new Permission("Update", permission1);
        Permission permission3 = new Permission("Delete", permission2);
        Assertions.assertThrows(ChainEndException.class, () -> new Permission("Remove", permission3));
    }
}
