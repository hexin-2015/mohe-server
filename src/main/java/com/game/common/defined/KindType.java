package com.game.common.defined;

public enum KindType {
	PLANT("plant",1),
	ANIMAL("animal",2),
	CAR("car",3),
	DISh("dish",4),
	FACE("face",5);
	 // 成员变量  
    private String name; 
    private int index;
    
    private KindType(String name,int index) {  
        this.name = name; 
        this.index = index; 
    } 
    
    public String getName() {
		return name;
	}
    
    public int getIndex() {
		return index;
	}
    
    public static KindType getByName(String name) {
    	if(name==null || name.isEmpty()){
    		return null;
    	}
		for (KindType type : values()) {
			if(type.getName().equals(name)){
				return type;
			}
		}
		return null;
	}
    
    public static boolean contains(String name) {
    	 //遍历查找   
        for(KindType s : values()){   
            if(s.getName().equals(name)){   
                return true;   
            }   
        }   
        return false;   
	}

}
