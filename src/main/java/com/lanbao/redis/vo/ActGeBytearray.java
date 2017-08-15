package com.lanbao.redis.vo;

// Generated 2014-10-9 18:51:28 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ActGeBytearray generated by hbm2java
 */
@Entity
@Table(name = "act_ge_bytearray", catalog = "jshoper3")
public class ActGeBytearray implements java.io.Serializable {

	private String id;
	private ActReDeployment actReDeployment;
	private Integer rev;
	private String name;
	private byte[] bytes;
	private Byte generated;
	private Set<ActReModel> actReModelsForEditorSourceValueId = new HashSet<ActReModel>(
			0);
	private Set<ActRuJob> actRuJobs = new HashSet<ActRuJob>(0);
	private Set<ActRuVariable> actRuVariables = new HashSet<ActRuVariable>(0);
	private Set<ActReModel> actReModelsForEditorSourceExtraValueId = new HashSet<ActReModel>(
			0);

	public ActGeBytearray() {
	}

	public ActGeBytearray(String id) {
		this.id = id;
	}

	public ActGeBytearray(String id, ActReDeployment actReDeployment,
			Integer rev, String name, byte[] bytes, Byte generated,
			Set<ActReModel> actReModelsForEditorSourceValueId,
			Set<ActRuJob> actRuJobs, Set<ActRuVariable> actRuVariables,
			Set<ActReModel> actReModelsForEditorSourceExtraValueId) {
		this.id = id;
		this.actReDeployment = actReDeployment;
		this.rev = rev;
		this.name = name;
		this.bytes = bytes;
		this.generated = generated;
		this.actReModelsForEditorSourceValueId = actReModelsForEditorSourceValueId;
		this.actRuJobs = actRuJobs;
		this.actRuVariables = actRuVariables;
		this.actReModelsForEditorSourceExtraValueId = actReModelsForEditorSourceExtraValueId;
	}

	@Id
	@Column(name = "ID_", unique = true, nullable = false, length = 64)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPLOYMENT_ID_")
	public ActReDeployment getActReDeployment() {
		return this.actReDeployment;
	}

	public void setActReDeployment(ActReDeployment actReDeployment) {
		this.actReDeployment = actReDeployment;
	}

	@Column(name = "REV_")
	public Integer getRev() {
		return this.rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BYTES_")
	public byte[] getBytes() {
		return this.bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Column(name = "GENERATED_")
	public Byte getGenerated() {
		return this.generated;
	}

	public void setGenerated(Byte generated) {
		this.generated = generated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actGeBytearrayByEditorSourceValueId")
	public Set<ActReModel> getActReModelsForEditorSourceValueId() {
		return this.actReModelsForEditorSourceValueId;
	}

	public void setActReModelsForEditorSourceValueId(
			Set<ActReModel> actReModelsForEditorSourceValueId) {
		this.actReModelsForEditorSourceValueId = actReModelsForEditorSourceValueId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actGeBytearray")
	public Set<ActRuJob> getActRuJobs() {
		return this.actRuJobs;
	}

	public void setActRuJobs(Set<ActRuJob> actRuJobs) {
		this.actRuJobs = actRuJobs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actGeBytearray")
	public Set<ActRuVariable> getActRuVariables() {
		return this.actRuVariables;
	}

	public void setActRuVariables(Set<ActRuVariable> actRuVariables) {
		this.actRuVariables = actRuVariables;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actGeBytearrayByEditorSourceExtraValueId")
	public Set<ActReModel> getActReModelsForEditorSourceExtraValueId() {
		return this.actReModelsForEditorSourceExtraValueId;
	}

	public void setActReModelsForEditorSourceExtraValueId(
			Set<ActReModel> actReModelsForEditorSourceExtraValueId) {
		this.actReModelsForEditorSourceExtraValueId = actReModelsForEditorSourceExtraValueId;
	}

}
