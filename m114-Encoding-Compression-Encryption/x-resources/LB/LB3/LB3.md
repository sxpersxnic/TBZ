# LB3

## Symmetric vs. Asymmetric

### Symmetric

- **Pros:**
- **Cons:**

### Asymmetric

- **Pros:**
- **Cons:**

### Hybrid (RSA-AES)

```mermaid
  sequenceDiagram
      participant Sender
      participant Receiver
      participant AES
      participant RSA

      Sender->>AES: Generate AES Key & IV
      Sender->>RSA: Encrypt AES Key with Receiver's Public Key
      RSA-->>Sender: Encrypted AES Key

      Sender->>AES: Encrypt Data with AES Key
      AES-->>Sender: Encrypted Data
      Sender->>Receiver: Send Encrypted AES Key & Encrypted Data

      Receiver->>RSA: Decrypt AES Key with Private Key
      RSA-->>Receiver: Decrypted AES Key

      Receiver->>AES: Decrypt Data with AES Key
      AES-->>Receiver: Plaintext Data
```

## Diffie-Hellman

1. Public params (prime)
2. Choose secret
3. Calculate
A=g^a % p
B=g^b % p
