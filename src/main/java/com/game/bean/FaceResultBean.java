package com.game.bean;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

public class FaceResultBean extends AiResultBean{
	
	private FaceBean result ;
	private String error_msg;
	private int cached;
	private int error_code;
	private int timestamp;
	
	public static class FaceBean{
		private int face_num;
		private ArrayList<Face> face_list = new ArrayList<Face>();	 
		public static class Face{
			private String face_token;
			private float face_probability;
			private Location location;
			private float beauty;
			private float age;
			private Gender gender;
			
			public String getFace_token() {
				return face_token;
			}
			public void setFace_token(String face_token) {
				this.face_token = face_token;
			}
			public float getFace_probability() {
				return face_probability;
			}
			public void setFace_probability(float face_probability) {
				this.face_probability = face_probability;
			}
			public Location getLocation() {
				return location;
			}
			public void setLocation(Location location) {
				this.location = location;
			}
			public float getBeauty() {
				return beauty;
			}
			public void setBeauty(float beauty) {
				this.beauty = beauty;
			}
			public float getAge() {
				return age;
			}
			public void setAge(float age) {
				this.age = age;
			}
			public Gender getGender() {
				return gender;
			}
			public void setGender(Gender gender) {
				this.gender = gender;
			}
			@Override
			public String toString() {
				return "Face [face_token=" + face_token + ", face_probability=" + face_probability + ", location="
						+ location + ", beauty=" + beauty + ", age=" + age + ", gender=" + gender + "]";
			}
			
		}
		
		public static class Gender{
			/**
			 * 可信度
			 */
			public float probability;
			/**
			 * female:女
			 * male:男
			 */
			public String type;
		}
		
		public int getFace_num() {
			return face_num;
		}
		public void setFace_num(int face_num) {
			this.face_num = face_num;
		}
		public ArrayList<Face> getFace_list() {
			return face_list;
		}
		public void setFace_list(ArrayList<Face> face_list) {
			this.face_list = face_list;
		}
		@Override
		public String toString() {
			return "FaceBean [face_num=" + face_num + ", face_list=" + face_list + "]";
		} 
		
	}
	
	
	
	
	public FaceBean getResult() {		
		return result;
	}
	
	public void setResult(FaceBean result) {
		this.result = result;
	}
	
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public int getCached() {
		return cached;
	}
	public void setCached(int cached) {
		this.cached = cached;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "FaceResultBean [error_msg=" + error_msg + ", cached=" + cached + ", error_code=" + error_code
				+ ", timestamp=" + timestamp + ", log_id=" + log_id + ", result=" + result + "]";
	}
	
	
	
	
	
}
