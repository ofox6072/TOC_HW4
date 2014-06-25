/***
TocHW4
�L��
F74006218
***/

import java.io.*;//File ,FileReader
import java.net.*;
import org.json.JSONException;
import org.json.JSONObject;//JSONObject
import org.json.JSONArray;//JSONArray
import org.json.JSONTokener;//JSONŪ��
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TocHw4 {
	
		 public static void main(String[] argv) throws Exception,IOException,FileNotFoundException{  //throws JSONException
			 
			 try{
				 if(argv.length == 1)
				 {
			 
					 URL pUrl = new URL(argv[0]);//���oargv URL�����}
					 BufferedReader iread = new BufferedReader(new InputStreamReader(pUrl.openStream(), "UTF-8"));//iread ŪURL
					 JSONArray arr = new JSONArray(new JSONTokener(iread));//��url��Ʃ�JJSONArray arr
					 
					
					 String block ;//��
					 String road;//��
					 String[] newroad = new String[2000];
					 int newroadnum[][];
					 newroadnum=new int[2000][10412];
					 int checknum[];
					 checknum=new int[2000];
					 int max=0;
					 int max1[]=new int [2000];
					 int min[]=new int [2000];
					 int year,i = 0,k=0;//year �~�Ai�ĴX��JSONArray arr,count���X�ӱ���ŦX���Ӽ�,sum�`����
					 boolean flag = true ,flag2=false;
					 int ch=0,ch1=0,ch2=0,ch3=0;
					 while (flag == true)
					 {
						 try{
							 JSONObject ob = arr.getJSONObject(i);
							 i++;
							 block = ob.getString("�m����");
							 road= ob.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P");
							 year = ob.getInt("����~��");
							 int price = ob.getInt("�`����");
							 for(int j=0;j<k;j++){//���P�_���S���A�X�{�L�����q�A�����ܦb�ofor���B�z�A�S�����ܨ�if(!flag2)�����q
								 String strr=newroad[j];
								 Pattern pattern = Pattern.compile(strr+".*");
								 Matcher matcher = pattern.matcher(road);
								 if(matcher.find()){
									 if(price>max1[j])
										 max1[j]=price;//���������̰������
									 if(price<min[j])
										 min[j]=price;//���������̧C�����
									 flag2=true;
									 if(newroadnum[j][year]==0){
										 newroadnum[j][0]++;//newroadnum[j][0]�b[0]�����`�@���X�Ӫ��~��
										 newroadnum[j][year]=1;
									 }
									 if(max<newroadnum[j][0])
										 max=newroadnum[j][0];//�̤j���~��O�_��
								 }
							 }
							 if(!flag2){	 //�����q
								 ch=road.indexOf('��');		//����						 
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
								 ch=road.indexOf('��');//����
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
								 ch=road.indexOf("�j�D");//���j�D
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
								 ch=road.indexOf('��');//����
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
								 if(newroadnum[j][0]==max){//���O�̤j�~���
									 
									 System.out.println(newroad[j]+", �̰������: "+max1[j]+" �̧C�����: " + min[j]);//��X
									
								 }
								 	 
							 }		 
				}//end if(argv.length == 4)
				 else//argv ��J���~
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