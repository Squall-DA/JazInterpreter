/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jazinterpreter;
import java.util.HashMap;
import java.util.Stack;
/**
 *
 * @author daniel
 */
public class Executer {
    MainEnvironment mainEnvironment;

        //Create stacks
    Stack<LocalEnvironment> EnvironmentStack;
    Stack<String> dataStack;

            //Pointers for maps
    HashMap<String, Integer>  LMem;
    HashMap<String, Integer>  RMem;
        
    public Executer(){
        mainEnvironment = new MainEnvironment();

        //Create stacks
        EnvironmentStack = new Stack();
        dataStack = new Stack();

            //Pointers for maps
        LMem = new HashMap();
        RMem = new HashMap();
    }
    
    public void ExecuteInstructions(){
        boolean isBranching = false;
        int i = 0;
        Node current = mainEnvironment.CommandList.get(i);
        this.LMem = this.mainEnvironment.Memory;
        this.RMem = this.mainEnvironment.Memory;

        while(current.getKeyword().compareTo("halt") != 0)
        {
            String keyword = current.getKeyword();
            String argument = current.getArgument();

            if(keyword.compareTo("push")==0)
            {
                InstructionSet.push(this.dataStack, argument);
            }

            else if(keyword.compareTo("pop")==0)
            {
                InstructionSet.pop(this.dataStack);
            }

            else if(keyword.compareTo("rvalue")==0)
            {
                InstructionSet.rvalue(this.dataStack, this.RMem, argument);
            }

            else if(keyword.compareTo("lvalue")==0)
            {
                InstructionSet.lvalue(this.dataStack, argument);
            }

            else if(keyword.compareTo(":=")==0)
            {
                InstructionSet.assign(this.dataStack, this.LMem);
            }

            else if(keyword.compareTo("copy")==0)
            {
                InstructionSet.copy(this.dataStack);
            }

            else if(keyword.compareTo("gofalse")==0)
            {
                int j = InstructionSet.goFalse(this.dataStack, this.mainEnvironment.LabelLocations, argument, i);
                current = this.mainEnvironment.CommandList.get(j);
                i = j;
                isBranching = true;
            }

            else if(keyword.compareTo("goto")==0)
            {
                int j = InstructionSet.goTo(this.mainEnvironment.LabelLocations, argument);
                current = this.mainEnvironment.CommandList.get(j);
                i = j;
                isBranching = true;
            }

            else if(keyword.compareTo("gotrue")==0)
            {
                int j = InstructionSet.goTrue(this.dataStack, this.mainEnvironment.LabelLocations, argument, i);
                current = this.mainEnvironment.CommandList.get(j);
                i = j;
                isBranching = true;
            }

            else if(keyword.compareTo("+")==0)
            {
                InstructionSet.add(this.dataStack);
            }

            else if(keyword.compareTo("-")==0)
            {
                InstructionSet.subtract(this.dataStack);
            }

            else if(keyword.compareTo("*")==0)
            {
                InstructionSet.multiply(this.dataStack);
            }

            else if(keyword.compareTo("/")==0)
            {
                InstructionSet.divide(this.dataStack);
            }

            else if(keyword.compareTo("div")==0)
            {
                InstructionSet.modulus(this.dataStack);
            }

            else if(keyword.compareTo("&")==0)
            {
                InstructionSet.logicalAnd(this.dataStack);
            }

            else if(keyword.compareTo("!")==0)
            {
                InstructionSet.logicalNot(this.dataStack);
            }

            else if(keyword.compareTo("|")==0)
            {
                InstructionSet.logicalOr(this.dataStack);
            }

            else if(keyword.compareTo("<>")==0)
            {
                InstructionSet.notEqual(this.dataStack);
            }

            else if(keyword.compareTo("<=")==0)
            {
                InstructionSet.lessThanOrEqual(this.dataStack);
            }

            else if(keyword.compareTo(">=")==0)
            {
                InstructionSet.greaterThanOrEqual(this.dataStack);
            }

            else if(keyword.compareTo("<")==0)
            {
                InstructionSet.lessThan(this.dataStack);
            }

            else if(keyword.compareTo(">")==0)
            {
                InstructionSet.greaterThan(this.dataStack);
            }

            else if(keyword.compareTo("=")==0)
            {
                InstructionSet.equal(this.dataStack);
            }

            else if(keyword.compareTo("print")==0)
            {
                InstructionSet.print(this.dataStack);
            }

            else if(keyword.compareTo("show")==0)
            {
                InstructionSet.show(argument);
            }

            else if(keyword.compareTo("begin")==0)
            {
                LocalEnvironment newLocalEnv = new LocalEnvironment();
                if(EnvironmentStack.empty())
                {
                    newLocalEnv.parentMemory = this.mainEnvironment.Memory;
                }

                else
                {
                    newLocalEnv.parentMemory = EnvironmentStack.peek().Memory;
                }

                EnvironmentStack.push(newLocalEnv);
                this.LMem = EnvironmentStack.peek().Memory;
                this.RMem.putAll(EnvironmentStack.peek().parentMemory);
            }

            else if(keyword.compareTo("call")==0)
            {
                this.LMem = EnvironmentStack.peek().Memory;
                this.RMem = EnvironmentStack.peek().Memory;
                EnvironmentStack.peek().backToCallingFunction = i+1;
                int j = this.mainEnvironment.LabelLocations.get(argument);
                current = this.mainEnvironment.CommandList.get(j);
                i = j;
                isBranching = true;
            }

            else if(keyword.compareTo("return")==0)
            {
                int j = EnvironmentStack.peek().backToCallingFunction;
                current = this.mainEnvironment.CommandList.get(j);
                i = j;
                isBranching = true;
                this.LMem.putAll(EnvironmentStack.peek().parentMemory);
                this.RMem = EnvironmentStack.peek().Memory;
            }

            else if(keyword.compareTo("end")==0)
            {
                this.LMem.putAll(EnvironmentStack.peek().parentMemory);
                this.RMem.putAll(EnvironmentStack.peek().parentMemory);
                EnvironmentStack.pop();
            }
            
            else if(keyword.compareTo("label")==0){
                ;   //Do nothing
            }

            else
            {
                System.out.println(current.getKeyword() + " is not a valid command\n");
            }

            if(!isBranching)
            {
                i++;
                current = this.mainEnvironment.CommandList.get(i);
            }

            
            isBranching = false;
        }
    }
}
