package com.game.common.defined;

import org.springframework.context.support.StaticApplicationContext;

public enum GoldReason {
	LIKE("like",1),
	//被点赞
	BELIKE("belike",101),
	
	GIVE("give",2),
	//被赠与
	BEGIVE("begive",102),
	
	COMMENT("comment",3),
	//更新个人信息
	UPDATE_USERINFO("update_userinfo",4),
	//上传颜值排名
	DORANK("dorank",4),
	//随机获得,1000以上需要通过概率获得
	RANDOM("random",1001);
	
	
	
	// 成员变量  
    private String name; 
    private int index;
    
    private GoldReason(String name,int index) {  
        this.name = name; 
        this.index = index; 
    } 
    
    public String getName() {
		return name;
	}
    
    public int getIndex() {
		return index;
	}
    
    public static GoldReason getByName(String name) {
    	if(name==null || name.isEmpty()){
    		return null;
    	}
		for (GoldReason type : values()) {
			if(type.getName().equals(name)){
				return type;
			}
		}
		return null;
	}
    
    public static boolean contains(String name) {
    	 //遍历查找   
        for(GoldReason s : values()){   
            if(s.getName().equals(name)){   
                return true;   
            }   
        }   
        return false;   
	}
    
    
    public static GoldReason getBeReason(GoldReason reason){
    	switch (reason) {
		case LIKE:
			return BELIKE;
		case GIVE:
			return BEGIVE;
		default:
			break;
		}
    	return null;
    }

}
