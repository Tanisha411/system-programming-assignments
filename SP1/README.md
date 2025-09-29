# SP1 Assignment
# Two-Pass Assembler – Pass 1

This program implements **Pass 1 of a two-pass assembler**, a classic system programming assignment.  
It takes assembly program instructions as input via the terminal and performs three key functions:

1. **Location Counter Processing**  
   - Assigns addresses to each instruction and data definition.  
   - Handles assembler directives (e.g., `START`, `END`, `ORIGIN`).  

2. **Intermediate Code Generation**  
   - Produces an intermediate representation of the source program.  
   - This intermediate output is later consumed by Pass 2 to generate the final object code.  

3. **Symbol Table Generation**  
   - Builds a **SYMTAB (Symbol Table)** mapping labels to their assigned addresses.  
   - Detects forward references and maintains necessary bookkeeping for Pass 2.  

---

## Why This Matters
Pass 1 of an assembler demonstrates understanding of how **assembly programs are processed for machine-level translation**.  
Concepts like location counters, symbol resolution, and intermediate representations are foundational to:
- Compiler construction  
- Linker/loader design  
- Modern build systems and toolchains  

An interviewer reviewing this project will see that it covers:
- Efficient handling of forward references  
- The design of intermediate representations  
- The basics of symbol resolution in language processing  

---

## How to Run
```bash
javac SP1.java
java SP1
