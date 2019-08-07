<h2>Vault-Boot-Mongo</h2>

- To write a record in Collection
  ```
  curl -X POST -H "Content-Type: application/json" -d "{\"firstName\":\"James\", \"lastName\":\"Bond\"}" localhost:8080/v1/db/write
  ```

- To read all records in Collection
  ```
  curl -X GET localhost:8080/v1/db/find
  ```

<h2>Environment Variables list to be initialized be the correct value</h2>
- vaultHost
- vaultToken
- mongoHost    
  
<h2>Hack-o-Shack</h2>

- The reason why VaultConfiguration is implementing EnvironmentAware interface is:
  - The Environment bean was giving NPE before implementing this interface.  
  - I believe there were some lifecycle issues with Spring and the EntityManagerFactory, and you might have fallen foul of those (fixed in 4.0.0.RC1) - if your @Configuration class gets instantiated super early, it might not be eligible for autowiring. You can probably tell from the log output if that is the case.
  - Just out of interest, did you know that the functionality provided by your JpaConfig and PropertyConfig is already presetn out of the box if you use @EnableAutoConfiguration (as long as you @ComponentScan that package where your repositories are defined)? 
  