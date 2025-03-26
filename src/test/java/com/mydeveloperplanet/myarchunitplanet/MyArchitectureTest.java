package com.mydeveloperplanet.myarchunitplanet;

import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.core.domain.JavaModifier.PRIVATE;

import java.util.List;

import com.enofex.taikai.Taikai;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

class MyArchitectureTest {

    @Test
    void shouldFulfillConstraints() {
        Taikai.builder()
                .namespace("com.mydeveloperplanet.myarchunitplanet")
                .spring(spring -> spring
                        .boot(boot -> boot
                                .springBootApplicationShouldBeIn("com.mydeveloperplanet.myarchunitplanet"))
                        )
                .build()
                .checkAll();
    }

}
