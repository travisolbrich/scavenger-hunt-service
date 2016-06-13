package com.travisolbrich.scavenger.hunt.repository;

import com.travisolbrich.scavenger.hunt.entity.ClueEntity;
import com.travisolbrich.scavenger.hunt.entity.CodeTagEntity;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeTagRepository extends CrudRepository<CodeTagEntity, Long> {

    CodeTagEntity findOneByUnlockCode(String unlockCode);
}