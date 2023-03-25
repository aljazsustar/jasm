package com.example.jasm.v1;

import javax.annotation.security.PermitAll;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
@PermitAll
public class API extends Application {

}
