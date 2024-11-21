package com.alishahidi.tasco.application.commands;

import com.alishahidi.tasco.TascoApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TascoApplication.class)
class AddTaskCommandTest {

}