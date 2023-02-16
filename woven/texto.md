### Overview

The basic steps of the workflow we're trying to build a system for are: 
1. We start a client onboarding.
2. Client requests onboarding tasks.
3. We assign eatch task to one or more CSR.

### Assumptions

- Clients are using a browser or a mobile App to ask for the services.
- Each onboarding task is requested individually.
- Clients do their requests one-by-one, not in batch.
- The system is going to handle the API requests asynchronously.
- The system will follow the event driven architecture.


### System Components

The top-level components of the system that will handle this are:
1. We would need a LoadBalancer to distribute the load among our servers.
2. One or more application servers to run our code that handle the API requests.
3. A messaging system with delivery guarantees of type exactly one (kafka, for example).
3. One or more application servers to run a code that listens to new tasks and call the assignment routine.
4. A database to store the all the information needed to do the  job. 


### Happy Path

If all goes according to plan, here's how the system would work like this:
1. Client requests a service.
2. The request is received by the system, validated and delivered to the messaging system.
3. The client is notified with the proper message (success).
4. The message consumer server receives the new task, validates it and calls the task assignment routine.
5. The assignments are calculated and stored in the database.
6. Probably there is some sort of message to the CSR team notifying the new assignment.

### Failure Resilience

Here are some thoughts about how to ensure the system stays running smoothly. When things go wrong, we can minimize the damage by using the strategies below:


1. **Step 1** - The client requests fails:

The frontend should notify the client to try again later with the proper message.\
The frontend should try to notify the development/devops team that the system is not working properly.


2. **Step 2** - The messaging system fails:

We should log all the info related to the problem found.\
We should notify the frontend about the problem\
The frontend should notify the client to try again later with the proper message.\ 
We should try to notify the development/devops team that the system is not working properly.

3. **Step 2** - The request is malformed:

We should log all the info related to the problem found.\
We should notify the frontend about the problem\
The frontend should notify the client to correct the request and try again. 

4.  **Step 3** - The system goes offline before the client is notified about the success:

The frontend should try to do the request again, as the loadbalancer will probably give the request to a running application server.\ 
In the case of another fail in the row, the user should be notified by the frontend.\
The frontend should report the system failure to another monitoring system.

5. **Step 4** - The message consumer fails dealing with the request.

The message consumer should start checking if the request is well formed and valid.
to be handled has already been processed.\
In this case it should log the situation and check the next request.\


### Data Integrity

To ensure that all of our data is clean, let's 