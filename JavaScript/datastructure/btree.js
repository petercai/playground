/**
 * Created by pcai01 on 11/08/2016.
 */
var o = console;

function Node(element, left, right){
    this.e = element;
    this.left = left;
    this.right = right;

    this.show = function(){return this.e;}
}

function BTree(){
    this.root = null;
    this.insert = function(item){
        var node = new Node(item, null, null);
        if( this.root == null ) this.root = node;
        else{
            var cur = this.root;
            var parent;
            while(true){
                parent = cur;
                if( item < cur.e ){
                    cur = parent.left;
                    if( cur == null ){
                        parent.left = node;
                        break;
                    }
                }
                else if( item > cur.e ){ // no duplicate item
                    cur = parent.right;
                    if( cur == null ){
                        parent.right = node;
                        break;
                    }
                }
            }
        }
    }
    this.result;
    this.inOrder = function(){
        var result = [];
        var inorder = function(node){
            if( node !=  null ){
                inorder(node.left);
                result.push(node.show());
                inorder(node.right);
            }
        }
        inorder(this.root);
        return result;

    }

    this.preOrder = function(node){
        var result = [];
        var preorder = function(node){
            if(node!=null){
                result.push(node.show());
                preorder(node.left);
                preorder(node.right);
            }
        }
        preorder(this.root);
        return result;
    }
    this.postOrder = function(node){
        var result = [];
        var postorder = function(node){
            if(node!=null){
                postorder(node.left);
                postorder(node.right);
                result.push(node.show());
            }
        }
        postorder(this.root);
        return result;
    }
}

var nums = new BTree();
nums.insert(23);
nums.insert(45);
nums.insert(16);
nums.insert(37);
nums.insert(3);
nums.insert(99);
nums.insert(22);
o.log("Inorder traversal: ");
o.log(nums.inOrder());

o.log("Preorder traversal: ");
o.log(nums.preOrder());

o.log("Postorder tranversal: ");
o.log(nums.postOrder());