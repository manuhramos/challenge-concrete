package br.com.concrete.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public class UserExcludeStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {

        return (f.getDeclaringClass() == Phone.class &&
                (f.getName().equals("id")|| f.getName().equals("user")));
    }

}
