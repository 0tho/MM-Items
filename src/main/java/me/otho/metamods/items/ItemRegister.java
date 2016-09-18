package me.otho.metamods.items;

import com.google.gson.JsonObject;

import me.otho.metamods.core.registry.IRegister;

public class ItemRegister implements IRegister {

	@Override
	public void register(JsonObject obj) {
		System.out.println( "It works" );
		System.out.println( obj.toString());
	}

}
