package br.com.concrete.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public class UUIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        try {
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            String digest = DatatypeConverter.printHexBinary(salt.digest());
            return digest;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
