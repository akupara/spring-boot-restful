package in.xroc.dev.demo.utils;

import com.fasterxml.uuid.Generators;
import io.seruco.encoding.base62.Base62;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

import static com.fasterxml.uuid.impl.UUIDUtil.asByteArray;

public class Base62UuidGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return new String(Base62.createInstance().encode(asByteArray(Generators.timeBasedGenerator().generate())));
    }
}
