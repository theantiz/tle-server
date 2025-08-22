# TLE Server

## From TLE on LeetCode to Handling 1 Million Requests with a Java Multithreaded Server

## Overview

TLE Server chronicles my journey from struggling with Time Limit Exceeded (TLE) errors on LeetCode to successfully building a Java multithreaded web server capable of handling 1 million client requests. This project uniquely blends algorithmic learning with hands-on systems programming and foundational computer networks concepts.

## Detailed Journey

1. **Encountering TLE on LeetCode**  
   While practicing Data Structures and Algorithms on LeetCode, I often hit Time Limit Exceeded (TLE) errors. This experience highlighted that algorithmic efficiency is important but not the sole factor in performance optimization.

2. **Shifting Focus to Computer Networks**  
   Preparing for a Computer Networks quiz introduced me to how data is transmitted and how servers manage multiple clients simultaneously, which sparked my interest in applying these concepts practically.

3. **Implementing a Single-Threaded Server in Java**  
   I started by building a single-threaded Java server using socket programming to understand basic server-client communication. It worked but could only handle one request at a time, limiting its scalability under load.

4. **Building a Multithreaded Server using Thread Pools**  
   I then enhanced the server to handle concurrent requests via a fixed-size thread pool (`ExecutorService`). Using this thread pool, the server successfully accepted and processed 1 million client requests, significantly improving throughput and scalability.

## Technical Details & Challenges

- Used Java’s `ServerSocket` and `Socket` for TCP communication.
- Managed client requests with a fixed-size thread pool to optimize resource usage and concurrency.
- Synchronized shared resources carefully to avoid concurrency issues.
- Conducted stress testing simulating 1 million requests to validate scalability.
- Tuned JVM and OS parameters to handle large loads efficiently.
- Implemented graceful shutdown and resource cleanup.

## Results & Learnings

- The single-threaded server is straightforward but inadequate for large-scale use.
- The multithreaded TLE Server efficiently managed 1 million requests using a thread pool, demonstrating the importance of concurrency.
- Developed expertise in:
  - Multithreading and synchronization
  - Socket programming and network communication
  - Backend scalability and performance optimization
  - Applying computer networks theory in practice

---

TLE Server is not just a project—it’s a practical bridge connecting algorithmic problem solving with real-world system design and network programming skills.
