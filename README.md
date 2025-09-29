# System Programming Assignments

Classic systems programming labs: assembler, loader, linker, macro processor (typically taught under Operating Systems / Compiler Design)

This repository contains my **System Programming assignments**, implemented in Java.  
Each program demonstrates key concepts in assembler design, macro processing, and lexical analysis, inspired by the classical problems in system software.

---

## Contents

### Assignment 1 – Two-Pass Assembler (Pass 1)
- Location Counter (LC) processing  
- Assembly code → Intermediate code  
- Symbol Table (SYMTAB) generation  
- Error checks:
  - Invalid mnemonics  
  - Missing comma between operands  

### Assignment 2 – Two-Pass Assembler (Pass 2)
- Location Counter (LC) processing  
- Assembly code → Intermediate code (IC)
- Literal Table generation
- Pool Table generation

### Assignment 3 – Intermediate Code to Machine Code
- Location Counter (LC) processing  
- Intermediate code → Machine code  

### Assignment 4 – Macro Processor
- Macro expansion: final assembly code with macros expanded  
- Generation and use of:
  - **MNT** (Macro Name Table)  
  - **MDT** (Macro Definition Table)  
  - **EVNTAB** (Expansion Variable Name Table)  
  - **EVTAB** (Expansion Variable Table)  
  - **APTAB** (Argument Parameter Table)  

### Assignment 5 – Lexical Analyser for C
- Lexical analysis of a C program  
- Symbol table generation  

---

## How to Run
Each assignment is self-contained within its folder (`SP1/`, `SP2/`, etc.).  
To run any program:
1. Open the `.java` file in your IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).  
2. Run the program.  
3. Provide the required input through the terminal when prompted.
4. Assignments 4 and 5 use file handling. The respective input files are present in those folders.

---

## Notes
These assignments have been implemented with respect to the **hypothetical assembly language** described in the textbook  
**_Systems Programming_ by D.M. Dhamdhere**.  
