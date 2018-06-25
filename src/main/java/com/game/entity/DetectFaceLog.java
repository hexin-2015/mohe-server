package com.game.entity;

import com.game.bean.AiResultBean;
import com.game.bean.FaceResultBean;
import com.game.bean.FaceResultBean.FaceBean;


/**
 * @author admin
 *
 */
public class DetectFaceLog extends DetectLog {
	private String face_token;
	private int sex;
	private int age;
	private float beauty;
	private int be_rank;
	
	public String getFace_token() {
		return face_token;
	}

	public void setFace_token(String face_token) {
		this.face_token = face_token;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getBeauty() {
		return beauty;
	}

	public void setBeauty(float beauty) {
		this.beauty = beauty;
	}
	

	public int getBe_rank() {
		return be_rank;
	}

	public void setBe_rank(int be_rank) {
		this.be_rank = be_rank;
	}

	@Override
	public String toString() {
		return "DetectFaceLog [face_token=" + face_token + ", sex=" + sex + ", age=" + age + ", beauty=" + beauty
				+ ", id=" + id + ", openid=" + openid + ", path=" + path + ", time=" + time + "]";
	}

	@Override
	public void setData(AiResultBean data) {

		FaceResultBean resData = (FaceResultBean)data;
		FaceBean result = resData.getResult();
		if(result == null){
			return;
		}
		age = (int)result.getFace_list().get(0).getAge();
		beauty = result.getFace_list().get(0).getBeauty();
		face_token = result.getFace_list().get(0).getFace_token();
		String type = result.getFace_list().get(0).getGender().type;
		sex = type.equals("male") ? 1 : 2 ;
		
	}
	
	

}
