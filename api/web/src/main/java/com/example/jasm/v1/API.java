package com.example.jasm.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.annotation.security.PermitAll;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
@PermitAll
@CrossOrigin
public class API extends Application {

}
