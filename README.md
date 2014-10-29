Spring-hibernate-project
========================

A backend for enterprise application using spring, hibernate and JPA which i made back in 2012.

A few notices:

1. This is only a server side project (logic and dao tiers), the "front-end" is written in swing.
2. I've wrote 2 implementations for the DAO tier - one in hibernate and one in JPA, you can choose which implemntation you want by specify the "hibernate" or "JPA" in the properties file, and the implementation will be injected.
3. There are a few software conventions which i wasn't aware of when i wrote this project (my first enterprise project) like:
   Naming conventions (using caseCamel for variables etc').
