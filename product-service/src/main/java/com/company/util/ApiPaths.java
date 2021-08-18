package com.company.util;

public class ApiPaths {

    private static final String BASE_PATH = "/api";

    public static final class ProductCtrl{
        public static final String CTRL = BASE_PATH + "/products";
    }

    public static final class CategoryCtrl{
        public static final String CTRL = BASE_PATH + "/category";
    }

    public static final class FileStoreCtrl{
        public static final String CTRL = BASE_PATH + "/filestore";
    }
}
