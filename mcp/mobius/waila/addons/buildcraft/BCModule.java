package mcp.mobius.waila.addons.buildcraft;

import java.lang.reflect.Method;
import java.util.logging.Level;

import mcp.mobius.waila.mod_Waila;
import mcp.mobius.waila.addons.ExternalModulesHandler;
import net.minecraftforge.common.ForgeDirection;

public class BCModule {

	public static Class  TileTank       = null;
	public static Class  IPowerReceptor = null;
	public static Class  PipeTransportPower = null;
	public static Class  TileGenericPipe    = null;
	public static Class  TileEngine         = null;
	
	public static Method TileTank_getTankInfo      = null;

	public static void register(){
		try {
			Class ModBuildcraftFactory = Class.forName("buildcraft.BuildCraftFactory");
			mod_Waila.log.log(Level.INFO, "Buildcraft|Factory mod found.");
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.INFO, "Buildcraft|Factory mod not found. Skipping.");	
			return;
		}		
		
		try{
			TileTank            = Class.forName("buildcraft.factory.TileTank");
			TileTank_getTankInfo      = TileTank.getMethod("getTankInfo", ForgeDirection.class);
			
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.WARNING, "[BC] Class not found. " + e);
			return;
		} catch (NoSuchMethodException e){
			mod_Waila.log.log(Level.WARNING, "[BC] Method not found." + e);
			return;	
		}
		
		ExternalModulesHandler.instance().addConfig("Buildcraft", "bc.tankamount");
		ExternalModulesHandler.instance().addConfig("Buildcraft", "bc.tanktype");
		ExternalModulesHandler.instance().registerHeadProvider(new HUDHandlerBCTanks(), TileTank);			
		ExternalModulesHandler.instance().registerBodyProvider(new HUDHandlerBCTanks(), TileTank);
	}
	
}
