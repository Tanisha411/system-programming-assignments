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
  
4. **Error Checking**  
   - Invalid mnemonic 
   - Comma missing after first operand 

---

## How to Run
The program can be run directly in any Java IDE:
- Open `SP1.java` in your IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).  
- Run the program.  
- Enter assembly code instructions through the terminal when prompted.  

The assembler will process your input and generate:
- An intermediate representation of the program  
- A symbol table with resolved addresses  
- Performs 2 error checks - invalid mnemonic and comma missing after first operand
---

## Sample Output
<img width="140" height="317" alt="image" src="https://github.com/user-attachments/assets/2d42bab2-819f-4056-b5db-0b6d99e221e2" />

<img width="113" height="173" alt="image" src="https://github.com/user-attachments/assets/1f290751-58ed-423e-9d2d-70db67215bf3" />

<img width="271" height="70" alt="image" src="https://github.com/user-attachments/assets/20427c90-0f0d-464c-bbfe-ced31863455b" />

<img width="259" height="96" alt="image" src="https://github.com/user-attachments/assets/ea31160f-8701-42b6-8260-0d3a8fd66381" />

<img width="258" height="85" alt="image" src="https://github.com/user-attachments/assets/71baf548-7390-4b14-8ef4-8c057e88757f" />

---

## Notes
This assignment has been implemented with respect to the **hypothetical assembly language** described in the textbook  
**_Systems Programming_ by D.M. Dhamdhere**.  
