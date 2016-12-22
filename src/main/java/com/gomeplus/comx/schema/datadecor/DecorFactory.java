package com.gomeplus.comx.schema.datadecor;

import com.gomeplus.comx.schema.datadecor.decors.*;
import com.gomeplus.comx.utils.config.Config;

/**
 * Created by xue on 12/19/16.
 */
public class DecorFactory
{
    public static AbstractDecor create(Config conf, String forceType) throws UnknownDecorTypeException
    {
        String type;
        if (forceType.isEmpty()) {
           type = forceType;
        } else {
           type = conf.str(AbstractDecor.FIELD_TYPE, AbstractDecor.TYPE_EACH);
        }
        switch (type) {
            case "each":        return new EachDecor(conf);
            case "root":        return new RootDecor(conf);
            case "composition": return new CompositionDecor(conf);

            default: throw new UnknownDecorTypeException();
        }
    }

    public static AbstractDecor create(Config conf) throws UnknownDecorTypeException
    {
        return DecorFactory.create(conf, "");
    }
}

