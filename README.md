# transfer-service

### Implementation notes:
* The same data model is used in all layers for simplicity;
* JSR 303: Bean Validation should be considered instead of custom solution;
* In-memory data store is used for simplicity;
* Event consumer and producer applications are in the same process for simplicity;
* Balance reservation is omitted for simplicity
