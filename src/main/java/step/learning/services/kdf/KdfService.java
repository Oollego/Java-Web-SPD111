package step.learning.services.kdf;

/**
 * Key Derivation Function service
 * By RFC 2898
 * */

public interface KdfService {
     public String derivedKey(String password, String salt);
}
