#include <string.h>
#include <openssl/bio.h>
#include <openssl/evp.h>

unsigned char *encode(unsigned char *data, int len) {
    //printf("BASE64 ENCODING %d chars: %s\n", len, data);
    BIO *bio, *b64;
    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bio = BIO_new(BIO_s_mem());
    BIO_push(b64, bio);
    BIO_write(b64, data, len);
    BIO_flush(b64);
    //unsigned char* output = malloc(len + 1);
    unsigned char* output;
    int outlen = BIO_get_mem_data(bio, &output);
    output[outlen] = '\0';
    //BIO_free_all(bio);
    //printf("BASE64 ENCODED: %d chars: %s\n", outlen, output);
    return output;
}

unsigned char *decode(char *data, int len) {
    //printf("BASE64 DECODING %d chars: %s\n", len, data);
    BIO *b64, *bmem;
    unsigned char *output = (unsigned char *) malloc(len + 1);
    FILE* stream = fmemopen(data, len, "r");
    b64 = BIO_new(BIO_f_base64());
    bmem = BIO_new_fp(stream, BIO_NOCLOSE);
    bmem = BIO_push(b64, bmem);
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    int outlen = BIO_read(bmem, output, len);
    output[outlen] = '\0';
    BIO_free_all(bmem);
    fclose(stream);
    //printf("BASE64 DECODED: %d chars: %s\n", outlen, output);
    return output;
}
