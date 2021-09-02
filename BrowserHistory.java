/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlisted;

/**
 *
 * @author Ayeni O. Femi
 */
public class BrowserHistory {
    private LinkedListed history = new LinkedListed();
    private int current_index = -1;
    
    public BrowserHistory(int homepage){
        history = new LinkedListed();
        history.addToHead(homepage);
        current_index = 0;
    }
    
    public void visit(int url){
        if(history.contains(url)){
            int index = history.getIndexOfElement(url);
            history.removeFromIndex(index);
        }else{
            history.addToTail(url);
        }
        current_index = this.history.size();
    }
    
    public String back(int steps){
        int index_to_go = current_index - steps;
        if(index_to_go < 0){
            index_to_go = 0;
        }
        current_index = index_to_go;
        return ""+this.history.getElementAtIndex(index_to_go);
        
    }
    
    public String forward(int steps){
        int index_to_go = current_index + steps;
        if(index_to_go >= this.history.size())
            index_to_go = this.history.size() - 1;
        current_index = index_to_go; 
        return "" + history.getElementAtIndex(index_to_go);
    }
}
