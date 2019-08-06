<h2>Vault-Boot-Mongo</h2>

- To write a record in Collection
  ```
  curl -X POST -H "Content-Type: application/json" -d "{\"firstName\":\"James\", \"lastName\":\"Bond\"}" localhost:8080/v1/db/write
  ```

- To read all records in Collection
  ```
  curl -X GET localhost:8080/v1/db/find
  ```