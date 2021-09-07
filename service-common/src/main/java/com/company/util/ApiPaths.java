package com.company.util;

public class ApiPaths {

    private static final String BASE_PATH = "/api";

    public static final class CustomerCtrl{
        public static final String CTRL = BASE_PATH + "/users";
    }

    public static final class AddressCtrl{
        public static final String CTRL = BASE_PATH + "/address";
    }

    public static final class AuthCtrl{
        public static final String CTRL = BASE_PATH + "/auth";
    }

    public static final class CartCtrl{
        public static final String CTRL = BASE_PATH + "/cart";
    }

}
