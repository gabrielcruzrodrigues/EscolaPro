package br.com.builders.escolar.security.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Getter
@Setter
@AllArgsConstructor
@Configuration
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

//    public RsaKeyProperties(@Value("${jwt.public.key}") String publicKeyPath,
//                            @Value("${jwt.private.key}") String privateKeyPath) {
//        this.publicKey = loadPublicKey(publicKeyPath);
//        this.privateKey = loadPrivateKey(privateKeyPath);
//    }
//
//    private RSAPublicKey loadPublicKey(String publicKeyPath) {
//        try {
//            FileInputStream fis = new FileInputStream(publicKeyPath);
//            byte[] publicKeyBytes = new byte[fis.available()];
//            fis.read(publicKeyBytes);
//            fis.close();
//
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
//        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new RuntimeException("Erro ao carregar chave pública", e);
//        }
//    }
//
//    private RSAPrivateKey loadPrivateKey(String privateKeyPath) {
//        try {
//            FileInputStream fis = new FileInputStream(privateKeyPath);
//            byte[] privateKeyBytes = new byte[fis.available()];
//            fis.read(privateKeyBytes);
//            fis.close();
//
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
//        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new RuntimeException("Erro ao carregar chave privada", e);
//        }
//    }


    public RsaKeyProperties() throws IllegalAccessException {
        KeyPair keyPair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}