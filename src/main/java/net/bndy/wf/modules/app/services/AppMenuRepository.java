package net.bndy.wf.modules.app.services;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bndy.wf.modules.app.models.Menu;

public interface AppMenuRepository extends JpaRepository<Menu, Long> {

}
