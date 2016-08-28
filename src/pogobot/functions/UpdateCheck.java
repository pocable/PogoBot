package pogobot.functions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import pogobot.lib.Reference;

public class UpdateCheck
{
  public static String newVer;
  
  public static boolean needsUpdate()
  {
    try
    {
      URL wb = new URL(Reference.UPDATESERVER);
      URLConnection uc = wb.openConnection();
      BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
      Boolean update = Boolean.valueOf(false);
      String resp = br.readLine();
      System.out.println(resp);
      if (resp.contains("version: "))
      {
        resp = resp.replaceAll("version: ", "");
        if (resp.equals(Reference.VERSION)) {
          return false;
        }
        System.out.println(resp);
        newVer = resp;
        return true;
      }
      System.out.println("No version has been recieved (Misconfigured Website)");
      


      return update.booleanValue();
    }
    catch (Exception e) {}
    return false;
  }
}
