package mc.troll.hu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener, CommandExecutor, Plugin{
	HashMap<Player, Long> freez = new HashMap();
	HashMap<Player, Long> brea = new HashMap();
	int mennyiseg = 0;

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "TrollX" + ChatColor.GREEN + "] " + ChatColor.DARK_GREEN + "----------------------------------------------");
        getServer().getConsoleSender().sendMessage("§a[§2TrollX§a]     §2+===============+");
        getServer().getConsoleSender().sendMessage("§a[§2TrollX§a]     §2|     TrollX    |");
        getServer().getConsoleSender().sendMessage("§a[§2TrollX§a]     §2|---------------|");
        getServer().getConsoleSender().sendMessage("§a[§2TrollX§a]     §2|     Plugin    |");
        getServer().getConsoleSender().sendMessage("§a[§2TrollX§a]     §2+===============+");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "TrollX" + ChatColor.GREEN + "] " + ChatColor.DARK_GREEN + "----------------------------------------------");
        getServer().getConsoleSender().sendMessage("§a[§2TrollX§a]     §2Current version: 1.0");
        getServer().getConsoleSender().sendMessage("§a[§2TrollX§a]     §2This is the latest version!");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "TrollX" + ChatColor.GREEN + "] " + ChatColor.DARK_GREEN + "----------------------------------------------");
    }
	
	   @EventHandler
	   public void mozog(PlayerMoveEvent e) {
	      Player p = e.getPlayer();
	      if (this.freez.containsKey(p) && System.currentTimeMillis() < (Long)this.freez.get(p)) {
	         e.setCancelled(true);
	      }
	   }

	   @EventHandler
	   public void tor(AsyncPlayerChatEvent e) {
	      Player p = e.getPlayer();
	      if (e.getMessage().equalsIgnoreCase("%Noel")) {
	          e.setCancelled(true);
	          p.sendMessage("§6The Plugin Made by Noel");
	      }
	   }

	   @EventHandler
	   public void tor(BlockBreakEvent e) {
	      Player p = e.getPlayer();
	      if (this.brea.containsKey(p) && System.currentTimeMillis() < (Long)this.brea.get(p)) {
	         e.setCancelled(true);
	      }
	   }

	   @EventHandler
	   public void lerak(BlockPlaceEvent e) {
	      Player p = e.getPlayer();
	      if (this.brea.containsKey(p) && System.currentTimeMillis() < (Long)this.brea.get(p)) {
	         e.setCancelled(true);
	      }
	   }
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(label.equalsIgnoreCase("troll")) {
			if (!sender.hasPermission("troll.admin")) {
	            sender.sendMessage("§cYou don't have permission to use this!");
	            return false;
	         }
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("tnt")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    target.getWorld().spawn(target.getLocation(), TNTPrimed.class);
                    sender.sendMessage("§bTNT summoned to "+target.getDisplayName());
                }else if(args[0].equalsIgnoreCase("lava")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    Location loc = target.getLocation();
                    int x = -1;
                    int z = -1;
                    for(int i = 0;i<9; i++) {
                        if(i%3 == 0) {
                            x++;
                            z=-1;
                        }else {
                            z++;
                        }
                        loc.setX(target.getLocation().getBlockX()+x);
                        loc.setZ(target.getLocation().getBlockZ()+z);
                        loc.getBlock().setType(Material.LAVA);
                        sender.sendMessage("§bLava summoned to "+target.getDisplayName());
                    }
                }else if(args[0].equalsIgnoreCase("inventory")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	target.openInventory(target.getInventory());
                	
                }else if(args[0].equalsIgnoreCase("creeper")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	target.getWorld().spawn(target.getLocation(), Creeper.class);
                	sender.sendMessage("§bCreeper summoned to "+target.getDisplayName());
                }else if(args[0].equalsIgnoreCase("drop")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	Player p = (Player)sender;
                	Location loc = target.getLocation();
                    Player x = Bukkit.getPlayer(args[1]);
                    if (x == null) {
                       sender.sendMessage("§bNo such user! ");
                       return false;
                    }

                    ItemStack itemStack = x.getItemInHand();
                    if (itemStack.getType() == Material.AIR) {
                       sender.sendMessage("§bThere is nothing " + x.getName() + " in hand!");
                       return false;
                    }

                    x.getWorld().dropItemNaturally(x.getLocation(), itemStack);
                    x.getPlayer().getInventory().remove(itemStack);
                    sender.sendMessage("§b" + x.getName() + " §bItem dropped!");
                 }else if(args[0].equalsIgnoreCase("hunger")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	target.setFoodLevel(0);
                	sender.sendMessage("§bHe is hungry now");
                }else if(args[0].equalsIgnoreCase("lagg")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	Location changed = target.getLocation().clone();
                	int x = -2;
                	changed.setX(target.getLocation().getBlockX()+x);
                    target.teleport(changed);
                    sender.sendMessage("§bHe is lagging");
                }else if (args[0].equalsIgnoreCase("lookup")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	Player p = (Player)sender;
                	Location loc = target.getLocation();
                    Player x = Bukkit.getPlayer(args[1]);
                    if (x == null) {
                       sender.sendMessage("§bNo such user! ");
                       return false;
                    }

                    loc = x.getLocation();
                    loc.setPitch(-90.0F);
                    x.teleport(loc);
                    x.setFoodLevel(x.getFoodLevel() - 2);
                    sender.sendMessage("§b" + x.getName() + " §bHe is looking up now");
                }else if (args[0].equalsIgnoreCase("fire")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	Player p = (Player)sender;
                	Location loc = target.getLocation();
                    Player x = Bukkit.getPlayer(args[1]);
                    p = (Player)sender;
                    x = Bukkit.getPlayer(args[1]);
                    if (x == null) {
                       sender.sendMessage("§bNo such user! ");
                       return false;
                       
                    }
                    x.setFireTicks(160);
                    sender.sendMessage("§b" + x.getName() + " §bIgnited! (For 8 seconds)");
                }else if (args[0].equalsIgnoreCase("freez")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	Location loc = target.getLocation();
                    Player x = Bukkit.getPlayer(args[1]);
                    if (x == null) {
                       sender.sendMessage("§bNo such user! ");
                       return false;
                    }

                    this.freez.put(x, System.currentTimeMillis() + 2000L);
                    sender.sendMessage("§b" + x.getName() + " §bFreezed for 2 seconds!");
                }else if (args[0].equalsIgnoreCase("kick")) {
                	Player target = Bukkit.getPlayer(args[1]);
                	Player p = (Player)sender;
                	Location loc = target.getLocation();
                    Player x = Bukkit.getPlayer(args[1]);
                    if (x == null) {
                       sender.sendMessage("§bNo such user! ");
                       return false;
                    }

                    x.kickPlayer("Lost connection : Internal Exception : Java.oi.IOExpection : An existing Connection was forcibly closed by the remote host.");
                    sender.sendMessage("§b" + x.getName() + " §bKicked!!");
                 }else if (args[0].equalsIgnoreCase("break")) {
                	 Player target = Bukkit.getPlayer(args[1]);
                 	Player p = (Player)sender;
                 	Location loc = target.getLocation();
                     Player x = Bukkit.getPlayer(args[1]);
                     if (x == null) {
                        sender.sendMessage("§bNo such user! ");
                        return false;
                     }

                     if (this.brea.containsKey(x) && System.currentTimeMillis() < (Long)this.brea.get(x)) {
                        sender.sendMessage("§b" + x.getName() + " §bHe can break!");
                        this.brea.put(x, System.currentTimeMillis());
                        return false;
                     }

                     this.brea.put(x, System.currentTimeMillis() + 980000L);
                     sender.sendMessage("§b" + x.getName() + " §bHe can't break!");
                  }

            }else {
                sender.sendMessage("§a-----§8[§3Troll§b§lCommands§8]§a-----\n"
                		+ "§b/troll tnt <player>\n"
                		+ "§b/troll lava <player>\n"
                		+ "§b/troll inventory <player>\n"
                		+ "§b/troll creeper <player>\n"
                		+ "§b/troll drop <player>\n"
                		+ "§b/troll hunger <player>\n"
                		+ "§b/troll lagg <player>\n"
                		+ "§b/troll lookup <player>\n"
                		+ "§b/troll fire <player>\n"
                		+ "§b/troll kick <player>\n"
                		+ "§b/troll break <player>\n"
                		+ "§b/troll freez <player>\n"
                		+ "§a-----§8[§3Troll§b§lCommands§8]§a-----");
            }
        }
		return false;
	}
	
	List<String> arguments = new ArrayList<String>();
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (arguments.isEmpty()) {
			arguments.add("tnt"); arguments.add("inventory");
			arguments.add("lava"); arguments.add("creeper");
			arguments.add("drop"); arguments.add("hunger");
			arguments.add("lagg"); arguments.add("lookup");
			arguments.add("fire"); arguments.add("freez");
			arguments.add("break"); arguments.add("kick");
		}
		
		List<String> result = new ArrayList<String>();
		if (args.length == 1) {
			 for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			 }
			 return result;
		}
		
		return null;

	}
	
}
