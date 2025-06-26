# spring-endpoint-performance-metrics

To start the web application via gradle/bootRun run the following command:
gradlew bootRun

To use the api directly, go to http://localhost:8080/api

## How to use

### Create a configuration

- Go to http://localhost:8080/api and select the Endpoint Test Configurations docket from the top right of the page. 
- Using either the non-bulk PUT or POST endpoints, save a new endpoint configuration object.
  - Please refer to the object shape in swagger to determine what fields/values you will need.
  - This will create data in the local h2 database for use in test runs.

### Run a test

- Once a configuration has been made, select the Endpoint Testing docket from the top right of the page.
- Using the run-test endpoint, set the id to the configuration that you would like to reference, then set the number of requests and threads you would like to use.
- Clicking the execute button will run a batched set of requests based on the configuration id selected and the thread and request count set here and will be saved off for future reference.

### Retrieve existing test results

- If you would like to review historical test runs, they are available via the api.
- Once a test has been run, select the Endpoint Run Results docket from the top right of the page.
- The results can be queried by their individual ids, or can be fetched in bulk by the available endpoints.
- Results can be manually added if wanted as well.