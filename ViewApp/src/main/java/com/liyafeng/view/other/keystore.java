package com.liyafeng.view.other;

public class keystore {


    /**
     * keytool -list -v -keystore /Users/pop/Documents/baoguan.keystore
     * 查看签名文件
     *
     *
     * Keystore type: JKS
     * Keystore provider: SUN
     * <p>
     * Your keystore contains 1 entry
     * <p>
     * Alias name: pop
     * Creation date: May 31, 2018
     * Entry type: PrivateKeyEntry
     * Certificate chain length: 1
     * Certificate[1]:
     * Owner: CN=baoguan, OU=qusu, O=qusu, L=beijing, ST=beijing, C=100000
     * Issuer: CN=baoguan, OU=qusu, O=qusu, L=beijing, ST=beijing, C=100000
     * Serial number: 43deaa83
     * Valid from: Thu May 31 15:44:28 CST 2018 until: Mon May 25 15:44:28 CST 2043
     * Certificate fingerprints:
     * MD5:  3E:37:76:3D:00:60:AE:D1:F8:E1:C3:6C:8F:BD:00:45
     * SHA1: 40:A5:79:E1:DB:82:02:93:25:90:3A:B6:32:B3:39:76:1F:15:F7:C0
     * SHA256: 29:2A:34:73:71:44:EE:45:73:11:91:48:12:46:FF:F7:B7:DF:C5:46:C7:28:2D:64:89:13:7E:C5:44:75:F8:81
     * Signature algorithm name: SHA256withRSA
     * Subject Public Key Algorithm: 2048-bit RSA key
     * Version: 3
     * <p>
     * Extensions:
     * <p>
     * #1: ObjectId: 2.5.29.14 Criticality=false
     * SubjectKeyIdentifier [
     * KeyIdentifier [
     * 0000: 79 6F D4 66 47 A2 DB 09   68 2C 18 02 0A 80 53 9B  yo.fG...h,....S.
     * 0010: 7E 82 31 78                                        ..1x
     * ]
     * ]
     * <p>
     * <p>
     * <p>
     * ******************************************
     * ******************************************
     * <p>
     * <p>
     * <p>
     * Warning:
     * The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /Users/pop/Documents/baoguan.keystore -destkeystore /Users/pop/Documents/baoguan.keystore -deststoretype pkcs12".
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
