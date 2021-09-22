package com.rest.training.dto;

import com.rest.training.dto.hateoas.LinkItem;
import com.rest.training.dto.hateoas.LinkVisitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDTO extends BaseDTO implements LinkItem {
	private Integer id;
	private String name;

	@Override
	public void accept(LinkVisitor visitor) {
		visitor.visit(this);
	}
}
