/***
TocHW4
林衡
F74006218
***/

import java.io.*;//File ,FileReader
import java.net.*;
import org.json.JSONException;
import org.json.JSONObject;//JSONObject
import org.json.JSONArray;//JSONArray
import org.json.JSONTokener;//JSON讀檔
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TocHw4 {
	
		 public static void main(String[] argv) throws Exception,IOException,FileNotFoundException{  //throws JSONException
			 
			 try{
				 if(argv.length == 1)
				 {
			 
					 URL pUrl = new URL(argv[0]);//取得argv URL的網址
					 BufferedReader iread = new BufferedReader(new InputStreamReader(pUrl.openStream(), "UTF-8"));//iread 讀URL
					 JSONArray arr = new JSONArray(new JSONTokener(iread));//把url資料放入JSONArray arr
					 
					
					 String block ;//區
					 String road;//路
					 String[] newroad = new String[2000];
					 int newroadnum[][];
					 newroadnum=new int[2000][10412];
					 int checknum[];
					 checknum=new int[2000];
					 int max=0;
					 int max1[]=new int [2000];
					 int min[]=new int [2000];
					 int year,i = 0,k=0;//year 年，i第幾個JSONArray arr,count有幾個條件符合的個數,sum總價元
					 boolean flag = true ,flag2=false;
					 int ch=0,ch1=0,ch2=0,ch3=0;
					 while (flag == true)
					 {
						 try{
							 JSONObject ob = arr.getJSONObject(i);
							 i++;
							 block = ob.getString("鄉鎮市區");
							 road= ob.getString("土地區段位置或建物區門牌");
							 year = ob.getInt("交易年月");
							 int price = ob.getInt("總價元");
							 for(int j=0;j<k;j++){//先判斷有沒有再出現過的路段，有的話在這for做處理，沒有的話到if(!flag2)切路段
								 String strr=newroad[j];
								 Pattern pattern = Pattern.compile(strr+".*");
								 Matcher matcher = pattern.matcher(road);
								 if(matcher.find()){
									 if(price>max1[j])
										 max1[j]=price;//那條路的最高成交價
									 if(price<min[j])
										 min[j]=price;//那條路的最低成交價
									 flag2=true;
									 if(newroadnum[j][year]==0){
										 newroadnum[j][0]++;//newroadnum[j][0]在[0]紀錄總共有幾個的年月
										 newroadnum[j][year]=1;
									 }
									 if(max<newroadnum[j][0])
										 max=newroadnum[j][0];//最大的年月記起來
								 }
							 }
							 if(!flag2){	 //切路段
								 ch=road.indexOf('路');		//切路						 
								 if(ch!=-1&&ch>3){
									newroad[k]=road.substring(0,ch+1);
									if(newroadnum[k][year]==0){
										 newroadnum[k][0]++;
										 newroadnum[k][year]=1;
									}
									max1[k]=price;
									min[k]=price;
									ch1=1;
								 }
								 ch=road.indexOf('街');//切街
								 if(ch!=-1&&ch1==0&&ch>3){
									newroad[k]=road.substring(0,ch+1);
									if(newroadnum[k][year]==0){
										 newroadnum[k][0]++;
										 newroadnum[k][year]=1;
									}
									max1[k]=price;
									min[k]=price;
									ch2=1;
								 }
								 ch=road.indexOf("大道");//切大道
								 if(ch!=-1&&ch2==0&&ch1==0&&ch>3){
										newroad[k]=road.substring(0,ch+1);
										if(newroadnum[k][year]==0){
											 newroadnum[k][0]++;
											 newroadnum[k][year]=1;
										}
										max1[k]=price;
										min[k]=price;
										ch3=1;
									 }
								 ch=road.indexOf('巷');//切巷
								 if(ch!=-1&&ch2==0&&ch1==0&&ch3==0&&ch>3){
									newroad[k]=road.substring(0,ch+1);
									if(newroadnum[k][year]==0){
										 newroadnum[k][0]++;
										 newroadnum[k][year]=1;
									}
									max1[k]=price;
									min[k]=price;
								 }
								 k++;
								 ch1=0;
								 ch2=0;
								 ch3=0;
							 }
							 
						 }
						 catch(JSONException e){
							 flag = false;
						 }
						 flag2=false;
						 
						 
					}//end while
					 
							 for(int j=0;j<k;j++){
								 if(newroadnum[j][0]==max){//路是最大年月數
									 
									 System.out.println(newroad[j]+", 最高成交價: "+max1[j]+" 最低成交價: " + min[j]);//輸出
									
								 }
								 	 
							 }		 
				}//end if(argv.length == 4)
				 else//argv 輸入錯誤
				 {
					 System.out.println("Unexpected arguments !");
				 }
			}//end try
			 
			catch(IOException e)
			{
				System.out.println("File Not Found!");
			}
			
		 }
		 
}