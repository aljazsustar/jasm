package com.jasm.jasm.v1.util;

import com.jasm.jasm.v1.resources.Jasm;

import java.security.Permission;

@SuppressWarnings("removal")
public class ExitSecurityManager extends SecurityManager {

    private static class ExitTrappedException extends SecurityException { }
    private SecurityManager _prevMgr = System.getSecurityManager();

    public void checkPermission(Permission perm)
    {
    }

    public void checkExit(int status)
    {
        super.checkExit(status);
        throw new ExitTrappedException(); //This throws an exception if an exit is called.
    }

    public SecurityManager getPreviousMgr() { return _prevMgr; }
}
