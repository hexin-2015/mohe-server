package com.game.common.defined;

/**
 * 随机获得率配置
 * @author admin
 *
 */
public class GoldSectionDefined {
	//随机区间
	public static final Section DEFAULT = new Section(1,10);
	
	public static final Section SECTION_5_15 = new Section(5,15);
	
	public static class Section{
		public int min;
		public int max;
		public Section(int min,int max) {
			this.min = min;
			this.max = max;
		}
	}
	
	
}
