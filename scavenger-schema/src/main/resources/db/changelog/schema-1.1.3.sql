--liquibase formatted sql

--changeset scavenger:6

ALTER TABLE snapshot_nodes MODIFY COLUMN signature TEXT NOT NULL;


--changeset scavenger:7
CREATE TABLE IF NOT EXISTS call_stack
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    customerId          BIGINT      NOT NULL,
    applicationId       BIGINT      NOT NULL,
    environmentId       BIGINT      NOT NULL,
    signatureHash       VARCHAR(32) NOT NULL,
    callerSignatureHash VARCHAR(32) NOT NULL,
    invokedAtMillis     BIGINT      NOT NULL,
    createdAt           TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP(),

    CONSTRAINT ix_call_stack_identity
        UNIQUE (customerId, applicationId, environmentId, signatureHash, callerSignatureHash)
) COLLATE = utf8mb4_0900_as_cs;
