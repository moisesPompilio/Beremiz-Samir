package com.samirAtivacao.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.samirAtivacao.modelo.UsuarioModelo;
import com.samirAtivacao.repository.UsuarioRepository;

@Service
public class BancoController {
	private UsuarioRepository repository = new UsuarioRepository() {
		
		@Override
		public <S extends UsuarioModelo> Optional<S> findOne(Example<S> example) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo> Page<S> findAll(Example<S> example, Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo> boolean exists(Example<S> example) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public <S extends UsuarioModelo> long count(Example<S> example) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public <S extends UsuarioModelo> S save(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Optional<UsuarioModelo> findById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void deleteById(Long id) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAllById(Iterable<? extends Long> ids) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll(Iterable<? extends UsuarioModelo> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void delete(UsuarioModelo entity) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Page<UsuarioModelo> findAll(Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo> S saveAndFlush(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo> List<S> saveAllAndFlush(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo> List<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UsuarioModelo getOne(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UsuarioModelo getById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public List<UsuarioModelo> findAllById(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo> List<S> findAll(Example<S> example, Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends UsuarioModelo> List<S> findAll(Example<S> example) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<UsuarioModelo> findAll(Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<UsuarioModelo> findAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void deleteAllInBatch(Iterable<UsuarioModelo> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAllInBatch() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAllByIdInBatch(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			
		}
	};
	 public String teste(UsuarioModelo vinda) {
		 repository.save(vinda);
		Long id = (long) 1;
		UsuarioModelo teste = repository.getById(id);
		return repository.getById(id).getNome();
		}
}
