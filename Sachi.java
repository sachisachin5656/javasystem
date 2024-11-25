import java.net.URL;

public class Sachi{

    public static void setSecurityManager(@SuppressWarnings("removal") SecurityManager sm) {
        if (allowSecurityManager()) {
            var callerClass = Reflection.getCallerClass();
            if (CallersHolder.callers.putIfAbsent(callerClass, true) == null) {
                URL url = codeSource(callerClass);
                final String source;
                if (url == null) {
                    source = callerClass.getName();
                } else {
                    source = callerClass.getName() + " (" + url + ")";
                }
                initialErrStream.printf("""
                        WARNING: A terminally deprecated method in java.lang.System has been called
                        WARNING: System::setSecurityManager has been called by %s
                        WARNING: Please consider reporting this to the maintainers of %s
                        WARNING: System::setSecurityManager will be removed in a future release
                        """, source, callerClass.getName());
            }
            implSetSecurityManager(sm);
        } else {
            // security manager not allowed
            if (sm != null) {
                throw new UnsupportedOperationException(
                    "The Security Manager is deprecated and will be removed in a future release");
            }
        }
    }

    public static void main (String args[]){
        void();
        
    }
}