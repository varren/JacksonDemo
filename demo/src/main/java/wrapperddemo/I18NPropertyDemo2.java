package wrapperddemo;

import java.util.ArrayList;
import java.util.Locale;

public class I18NPropertyDemo2<T>  extends ArrayList<I18NPropertyDemo2.Content<T>> {
    public static class Content<T> {
        public Locale i18n;
        public T val;

        @Override
        public String toString() {
            return "Locale: "+ i18n.toString() +" value: "+ String.valueOf(val);
        }
    }
}
