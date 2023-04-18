package app;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.*;


public class JPATest01 {

	public static void main(String[] args) {
		
		//CREACION DEL CRUD PARA USUARIOS 
		
		EntityManagerFactory Fabrica = Persistence.createEntityManagerFactory("JPA_sesion01");
		EntityManager xEManager = Fabrica.createEntityManager();

		//LISTA DE USUARIOS
		
		List<Usuario> xListaUsuario = xEManager.createQuery("Select a From Usuario a", Usuario.class).getResultList();
		System.out.println("==============================================");
		
		System.out.println("Lista de usuarios:");
		System.out.println("-----------------");
		for(Usuario u: xListaUsuario){
			System.out.println("Datos del Usuario: " 	  + u.getCodUsua() + " -- " + u.getNomUsua() + " -- " + u.getApeUsua() +
												   " -- " + u.getUsrUsua() + " -- " + u.getClaUsua() + " -- " + u.getFnaUsua() +
												   " -- " + u.getEstUsua());
		}
		System.out.println("==============================================");
		
		//INSERTAR NUEVO USUARIO
		
		System.out.println("Insertar nuevo Usuario: ");
		System.out.println("-------------------------------------------------------------------------");
		xEManager.getTransaction().begin();
		Usuario xUsuario = new Usuario();
		Date xFecha = new Date();
		Tipo xTipo = xEManager.find(Tipo.class, 2);
		xUsuario.setCodUsua(7);
		xUsuario.setNomUsua("Luis");
		xUsuario.setApeUsua("Rimac");
		xUsuario.setUsrUsua("luisrimac20@gmail.com");
		xUsuario.setClaUsua("luisrimac20");
		xUsuario.setFnaUsua(xFecha);
		xUsuario.setTbTipo(xTipo);
		xUsuario.setEstUsua(1);
		xEManager.persist(xUsuario);
		xEManager.getTransaction().commit();
		System.out.println("==============================================");
		
		
		//ACTUALIZACION
		System.out.println("Actualizamos Usuario : ");
		System.out.println("-------------------------------------------------------------------");
		xEManager.getTransaction().begin();
		Usuario xUuarioActualizar = xEManager.find(Usuario.class, 7);
		xUuarioActualizar.setClaUsua("10001");
		xEManager.persist(xUuarioActualizar);
		System.out.println("==============================================");
		
	
		//ELIMINACION
		System.out.println("Eliminar Usuario");
		System.out.println("-------------------------");
		xEManager.getTransaction().begin();
		Usuario xUsuarioEliminar = xEManager.find(Usuario.class, 7);
		xEManager.remove(xUsuarioEliminar);
		xEManager.getTransaction().commit();
		System.out.println("==============================================");		
		Fabrica.close();
		xEManager.close();
		
		//CREACION DEL CRUD PRODUCTOS
		
		//CREACION DE LA LISTA
		List<Producto> xListaProducto = xEManager.createQuery("Select a From Producto a", Producto.class).getResultList();
		System.out.println("==============================================");
			
				
				
		System.out.println("Lista de Productos:");
		System.out.println("-----------------");
		for(Producto p: xListaProducto){
		System.out.println("Productos: "+ p.getIdProd()+ " " + p.getDesProd()+ " "+ p.getStkProd()+ " "+p.getPreProd() + " " 
													+ p.getEstProd());
		}
				
		System.out.println("==============================================");
				
		//CREACION DEL CRUD PARA PRODUCTOS
		//INSERTAR UN NUEVO PRODUCTO
		System.out.println("Insertar un nuevo Producto : [P0021] ");
		System.out.println("-------------------------------------------------------------------------");
		xEManager .getTransaction().begin();
		Producto p = new Producto();
		BigDecimal monto = new BigDecimal(10.00);
		Byte xByte = new Byte("2");
		Categoria xCategoria = xEManager.find(Categoria.class, 1);
		Proveedor xProveedor = xEManager.find(Proveedor.class, 2);
		p.setIdProd("P0021");
		p.setDesProd("Leche Evaporada");
		p.setStkProd(10);
		p.setPreProd(monto);
		p.setEstProd(xByte);
		p.setTbCategoria(xCategoria);
		p.setTbProveedor(xProveedor);
		xEManager.persist(p);
		xEManager.getTransaction().commit();
		System.out.println("==============================================");

			
		//ACTUALIZAR PRODUCTOS 
				
		System.out.println("Actualizamos un Producto : [P0021] ");
		System.out.println("-------------------------------------------------------------------");
		xEManager.getTransaction().begin();
		Producto xActualizarProducto = xEManager.find(Producto.class, "P0021");
		xActualizarProducto.setDesProd("Leche xEvaporada");
		xEManager.persist(xActualizarProducto);
		xEManager.getTransaction().commit();
		System.out.println("==============================================");
				
		
		//ELIMINAR PRODUCTOS
				
		System.out.println("Eliminar Producto : [P0021]");
		System.out.println("-------------------------");
		xEManager.getTransaction().begin();
		Producto xProductoEliminar = xEManager.find(Producto.class, "P0021");
		xEManager.remove(xProductoEliminar);
		xEManager.getTransaction().commit();
		System.out.println("==============================================");		
		Fabrica.close();
		xEManager.close();
	
	}
}
