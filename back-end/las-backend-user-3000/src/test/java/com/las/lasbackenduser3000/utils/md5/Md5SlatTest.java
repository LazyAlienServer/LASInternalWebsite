package com.las.lasbackenduser3000.utils.md5;

import org.junit.jupiter.api.Test;

class Md5SlatTest {

    @Test
    void md5Slat() {
        int slat = Slat.slat();
        System.out.println(slat);
        System.out.println(Md5Slat.md5Slat("10", slat));

        System.out.println(Md5Slat.md5Slat("Martin", -100));
        System.out.println(Md5Slat.md5Slat("1", -100));
    }
}