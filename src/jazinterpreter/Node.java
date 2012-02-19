/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jazinterpreter;

/**
 *
 * @author daniel
 */
public class Node {

    private String keyword;
    private String argument;
    
    public Node (){
        keyword = null;
        argument = null;
    }
    
    public Node (String k, String a){
        keyword = k;
        argument = a;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the argument
     */
    public String getArgument() {
        return argument;
    }

    /**
     * @param argument the argument to set
     */
    public void setArgument(String argument) {
        this.argument = argument;
    }
    
}
