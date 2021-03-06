/**
 * Copyright (C) 2015 Red Hat, Inc. (jcasey@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.red.build.koji.kerberos;

import org.apache.kerby.asn1.Asn1FieldInfo;
import org.apache.kerby.asn1.EnumType;
import org.apache.kerby.asn1.ExplicitField;
import org.apache.kerby.asn1.type.Asn1Integer;
import org.apache.kerby.kerberos.kerb.type.EncKrbPrivPart;
import org.apache.kerby.kerberos.kerb.type.base.EncryptedData;
import org.apache.kerby.kerberos.kerb.type.base.KrbMessage;
import org.apache.kerby.kerberos.kerb.type.base.KrbMessageType;

/**
 * The KRB_PRIV message, as defined in RFC 1510 :
 * The KRB_PRIV message contains user data encrypted in the Session Key.
 * The message fields are:
 * <pre>
 * KRB-PRIV ::=         [APPLICATION 21] SEQUENCE {
 *       pvno[0]                   INTEGER,
 *       msg-type[1]               INTEGER,
 *       enc-part[3]               EncryptedData
 * </pre>
 */
public class KrbPriv extends KrbMessage {
    protected enum KrbPrivField implements EnumType {
        PVNO,
        MSG_TYPE,
        UNUSED, // NOTE: there is no [2] tag
        ENC_PART; // upstream code has a bug and puts this at tag 2 and not tag 3

        /**
         * {@inheritDoc}
         */
        @Override
        public int getValue() {
            return ordinal();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
            return name();
        }
    }
   static Asn1FieldInfo[] fieldInfos = new Asn1FieldInfo[] {
            new ExplicitField(KrbPriv.KrbPrivField.PVNO, Asn1Integer.class),
            new ExplicitField(KrbPriv.KrbPrivField.MSG_TYPE, Asn1Integer.class),
            new ExplicitField(KrbPriv.KrbPrivField.UNUSED, null), // NOTE: there is no [2] tag
            new ExplicitField(KrbPriv.KrbPrivField.ENC_PART, EncryptedData.class) // upstream code has a bug and puts this at tag 2 and not tag 3
    };

    /**
     * Creates a new instance of a KRB-PRIv message
     */
    public KrbPriv() {
        super(KrbMessageType.KRB_PRIV, fieldInfos);
    }

    private EncKrbPrivPart encPart;

    public EncryptedData getEncryptedEncPart() {
        return getFieldAs(KrbPriv.KrbPrivField.ENC_PART, EncryptedData.class);
    }

    public void setEncryptedEncPart(EncryptedData encryptedEncPart) {
        setFieldAs(KrbPriv.KrbPrivField.ENC_PART, encryptedEncPart);
    }


    public EncKrbPrivPart getEncPart() {
        return encPart;
    }

    public void setEncPart(EncKrbPrivPart encPart) {
        this.encPart = encPart;
    }
}
